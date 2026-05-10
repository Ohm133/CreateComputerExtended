package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.network.NetworkConnector;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredNetworkChange;
import dan200.computercraft.api.network.wired.WiredNode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class NetworkBlockEntity extends BlockEntity implements WiredElement {

    /*
     * Le noeud réseau CC:Tweaked
     *
     * IMPORTANT :
     * Le node reste CONSTANT pendant toute la vie du block entity.
     */
    private final WiredNode node;

    public NetworkBlockEntity(BlockPos pos, BlockState state) {

        super(ModBlockEntities.NETWORK_BLOCK_ENTITY.get(), pos, state);

        /*
         * Création du noeud réseau CC
         */
        this.node = ComputerCraftAPI.createWiredNodeForElement(this);
    }

    /*
     * Retourne le node réseau
     */
    @Override
    public WiredNode getNode() {
        return node;
    }

    /*
     * ID unique du sender
     */
    @Override
    public String getSenderID() {

        return worldPosition.toShortString();
    }

    /*
     * Niveau Minecraft
     */
    @Override
    public Level getLevel() {

        return level;
    }

    /*
     * Position physique du noeud
     */
    @Override
    public Vec3 getPosition() {

        return Vec3.atCenterOf(worldPosition);
    }

    /*
     * Callback CC quand le réseau change
     */
    @Override
    public void networkChanged(WiredNetworkChange change) {

        System.out.println("Réseau CC modifié !");
    }

    /*
     * Appelé quand le bloc est chargé
     */
    @Override
    public void onLoad() {

        super.onLoad();

        if (!level.isClientSide) {

            /*
             * Connecte automatiquement les voisins
             */
            NetworkConnector.connectAdjacent(this);
        }
    }

    /*
     * Nettoyage réseau
     */
    @Override
    public void setRemoved() {

        super.setRemoved();

        if (!level.isClientSide) {

            /*
             * Retire le node du réseau
             */
            node.remove();
        }
    }
}


/* je rapelle donc je souhaite un gearbox ( avec 4 entrés mecaniques) qui transmet aussi le réseau CC
 et quand il est connecté à un swivelbearing se pair de par et d'autre pour transmettre le 
 réseau CC et aussi un network encased sheft qui à la même fonction mais à la topologie d'un shaft, ils ont une classe 
 commune qui leur donne leurs caracteristiques communes.
*/